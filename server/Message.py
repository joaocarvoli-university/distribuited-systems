from uuid import uuid4


class Message:
    def __init__(self, messageType="", requestId="", serviceName="", methodName="", arguments=""):
        self.messageType = messageType
        self.requestId = str(uuid4())
        self.serviceName = serviceName
        self.methodName = methodName
        self.arguments = arguments

    def object_to_json(self):
        return self.__dict__

    def load_object(self, message):
        self.__dict__ = message
