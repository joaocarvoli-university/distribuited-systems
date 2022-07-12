class Communication:
    def __init__(self):
        self.client_address = None
        self.server_socket = None

    def get_request(self, serverSocket):
        message, client_address = serverSocket.recvfrom(2048)
        self.server_socket = serverSocket
        self.client_address = client_address
        return message.decode()

    def send_response(self, message):
        self.server_socket.sendto(str(message).encode(), self.client_address)