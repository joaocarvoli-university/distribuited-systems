import json
from services.weather import WeatherAPI as ServiceWeather


class Weather:
    def get_weather_temperature(self, args: list):
        params = get_params(args)
        city = params['city']
        self.tempoUser = ServiceWeather.WeatherCity(city)

        temperatura = self.tempoUser.get_weather_temperature()

        result = str(temperatura)

        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '')
        result = result.replace('"', '')
        response = list()
        response.append(result)
        return response

    def get_weather_clouds(self, args: list):
        params = get_params(args)
        city = params['city']
        self.tempoUser = ServiceWeather.WeatherCity(city)

        cloud = self.tempoUser.get_weather_clouds()

        result = str(cloud)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '')
        result = result.replace('"', '')
        print(result)
        response = list()
        response.append(result)
        return response

    def get_weather_wind(self, args: list):
        params = get_params(args)
        city = params['city']
        self.tempoUser = ServiceWeather.WeatherCity(city)

        wind = self.tempoUser.get_weather_wind()

        result = str(wind)
        result = result.replace('{', '')
        result = result.replace('}', '')
        result = result.replace("'", '')
        result = result.replace('"', '')

        print(result)
        response = list()
        response.append(result)
        return response


# pega o nome da cidade e coloca em params
def get_params(args: list):
    params = {}
    for i in args:
        param, value = i.split(':')
        params[param] = value
    return params
