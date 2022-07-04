import json


class RemoteObject:
    def __init__(self, message: json):
        self.serviceName = message['serviceName']
        self.methodName = message['methodName']

    def get_service_name(self):
        return self.serviceName

    def get_method_name(self):
        return self.methodName
