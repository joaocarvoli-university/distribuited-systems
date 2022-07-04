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
    response = dp.invoke(json.loads(message.decode()))

    duplicate_checker = Gdf.GandPy(cache_IDs_limit=1000, errorRaise=False)

    if duplicate_checker.message_ID_shall_pass(response['requestID']):
        serverSocket.sendto(str(response).encode(), clientAddress)
