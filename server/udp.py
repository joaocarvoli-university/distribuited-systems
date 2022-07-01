import json
from socket import *
from dispatcher import Dispatcher


serverPort = 7889
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))

print('The server is ready to receive')
while 1:
    message, clientAddress = serverSocket.recvfrom(1024)
    dp = Dispatcher()
    response = dp.invoke(json.loads(message.decode()))
    serverSocket.sendto(str(response).encode(), clientAddress)
