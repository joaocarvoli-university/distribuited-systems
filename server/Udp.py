import os
import json
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
cache = He.HandlingExceptions(cache_ids_limit=1000, logEnabled=False)

print(f'The server is running on port {serverPort}')
while True:
    dp = Dispatcher()
    messageObject = Message()
    message, clientAddress = serverSocket.recvfrom(1024)
    messageObject.serialize(json.loads(message.decode()))

    cache_response = cache.get_cache_from_request_id(messageObject.get_request_id())

    if cache_response is None:
        messageObject = dp.invoke(messageObject)
        cache.add_cache_to_request_id(messageObject.get_request_id(), messageObject)
    else:
        messageObject = cache_response

    serverSocket.sendto(str(messageObject.deserialize()).encode(), clientAddress)
