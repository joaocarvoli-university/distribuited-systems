import os
import json
from socket import *
from Dispatcher import Dispatcher
import gandPy as Gdf
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
    message, clientAddress = serverSocket.recvfrom(1024)
    
    dp = Dispatcher()
    decoded_message = json.loads(message.decode())

    cache = Gdf.GandPy(cache_ids_limit=1000, logEnabled=False)
    cache_response = cache.get_cache_from_requestID(decoded_message['requestID'])
    
    if not cache_response:
     response = dp.invoke(decoded_message)
     cache.add_cache_to_requestID(decoded_message['requestID'], response)
    else:
        response = cache_response

    serverSocket.sendto(str(response).encode(), clientAddress)
