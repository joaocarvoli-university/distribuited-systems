from json import dumps, loads
from socket import *


serverName = 'localhost'
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_DGRAM)
message = {
    "method":"add",
    "method":"mul",
    "method":"sum"
}
clientSocket.sendto(dumps(message).encode(),(serverName, serverPort))
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)
print(modifiedMessage)
clientSocket.close()