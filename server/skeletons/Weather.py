import json


class Weather:
    def get_weather_clouds(self, message: json):
        params = get_params(message)
        #params['']

    def get_weather_temperature(self, message: json):
        print('oi')

    def get_weather_wind(self, message: json):
        print('oie')


def get_params(message: json):
    params = {}
    for i in message['arguments']:
        param, value = i.split(':')
        params[param] = value
    return params
