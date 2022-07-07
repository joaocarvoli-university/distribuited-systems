import json
from services.weather import WeatherAPI as ServiceWeather


class Weather:
    def __init__(self, message: json):
        params = get_params(message)
        city = params['city']
        #lembrete
        self.tempoUser = ServiceWeather.WeatherCity(city)

    def get_weather_temperature(self, message: json):
        temperatura = json.loads(self.tempoUser.get_weather_temperature())

        message['result'] = temperatura
        message['messageType'] = 1
        del message['arguments']
        return message

    def get_weather_clouds(self, message: json):
        nuvens = json.loads(self.tempoUser.get_weather_clouds())

        message['result'] = nuvens
        message['messageType'] = 1
        del message['arguments']
        return message

    def get_weather_wind(self, message: json):
        vento = json.loads(self.tempoUser.get_weather_wind())

        message['result'] = vento
        message['messageType'] = 1
        del message['arguments']
        return message

#pega o nome da cidade e coloca em params
def get_params(message: json):
    params = {}
    for i in message['arguments']:
        param, value = i.split(':')
        params[param] = value
    return params

