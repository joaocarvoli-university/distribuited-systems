import os
import HandlingExceptions as He
from socket import *
from Dispatcher import Dispatcher
from Message import Message
from dotenv import load_dotenv

load_dotenv()
env = {
    'serverPort': os.getenv('SERVER_PORT')
}

serverPort = env['serverPort']
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', int(serverPort)))

print(f'The server is running on port {serverPort}')
while True:
    dp = Dispatcher()
    messageObject = Message()
    message, clientAddress = serverSocket.recvfrom(1024)
    messageObject.serialize(message)

    cache = He.HandlingExceptions(cache_ids_limit=1000, logEnabled=False)
    cache_response = cache.get_cache_from_request_id(messageObject.get_request_id())

    if not cache_response:
        response = dp.invoke(messageObject)
        cache.add_cache_to_request_id(messageObject.get_request_id(), response)
    else:
        response = cache_response

    serverSocket.sendto(str(response).encode(), clientAddress)
