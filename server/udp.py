import json
from socket import *
from urllib import request
from dispatcher import Dispatcher
import gandPy as gdf

serverPort = 7889
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))

print('The server is ready to receive')
while 1:
    message, clientAddress = serverSocket.recvfrom(1024)
    dp = Dispatcher()
    response = dp.invoke(json.loads(message.decode()))
    
    duplicate_checker = gdf.GandPy(cache_IDs_limit= 1000, errorRaise=False)
    
    if duplicate_checker.message_ID_shall_pass(response['requestID']):
        serverSocket.sendto(str(response).encode(), clientAddress)
