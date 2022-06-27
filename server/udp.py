from socket import *
from dispatcher import dispatcher


serverPort = 7889
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))

print('The server is ready to receive')
while 1:
    message, clientAddress = serverSocket.recvfrom(1024)
    print(message)
    #dispatcher.invoke(message)
    serverSocket.sendto(message, clientAddress)
