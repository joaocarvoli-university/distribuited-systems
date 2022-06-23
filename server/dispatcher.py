class dispatcher:
    def __init__(self):
        print("do nothing")

    def invoke(self, message: str) -> bool:
        """
        This method is responsible to select which skeleton of a specific service will be used
        :param message: The client message send from the client
        :return: the response message
        """
        message
        return False
