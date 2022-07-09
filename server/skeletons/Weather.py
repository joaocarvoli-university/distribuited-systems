import json
from services.weather import WeatherAPI as ServiceWeather


class Weather:
    def get_weather_temperature(self, args):

        params = get_params(args)
        city = params['city']
        self.tempoUser = ServiceWeather.WeatherCity(city)

        temperatura = self.tempoUser.get_weather_temperature()

        result = str(temperatura)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '"')
        return result

    def get_weather_clouds(self ,args):

        params = get_params(args)
        city = params['city']
        self.tempoUser = ServiceWeather.WeatherCity(city)

        cloud = self.tempoUser.get_weather_clouds()

        result = str(cloud)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '"')
        return result

    def get_weather_wind(self, args):

        params = get_params(args)
        city = params['city']
        self.tempoUser = ServiceWeather.WeatherCity(city)

        wind = self.tempoUser.get_weather_wind()

        result = str(wind)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '"')
        return result

#pega o nome da cidade e coloca em params
def get_params(message: json):
    params = {}
    for i in message['arguments']:
        param, value = i.split(':')
        params[param] = value
    return params

