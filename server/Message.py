from uuid import uuid4
import json


class Message:
    def __init__(self, messageType="", serviceName="", methodName="", arguments=""):
        self._messageType = messageType
        self._requestId = str(uuid4())
        self._serviceName = serviceName
        self._methodName = methodName
        self._arguments = arguments
        self._response = None
        self._errorMessage = None

    def deserialize(self):
        return self.__dict__

    def serialize(self, message):
        data = json.loads(message.decode())
        self.__dict__ = data

    def get_message_type(self):
        return self.messageType

    def set_message_type(self, type: int):
        self.messageType = type

    def get_request_id(self):
        return self.requestId

    def get_service_name(self):
        return self.serviceName

    def get_method_name(self):
        return self.methodName

    def get_arguments(self):
        return self.arguments

    # def delete_field(self, field: str):
    #    self.__dict__.pop(field)
