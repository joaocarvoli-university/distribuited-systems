import json
from socket import *
from dispatcher import Dispatcher


serverPort = 7889
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))

print('The server is ready to receive')
while 1:
    message, clientAddress = serverSocket.recvfrom(1024)
    Dispatcher.invoke(json.loads(message.decode()))
    print(message.decode())

    #serverSocket.sendto(message, clientAddress)
