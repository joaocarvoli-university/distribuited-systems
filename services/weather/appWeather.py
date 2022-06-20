import WeatherAPI as wth
import json

api_key = '207b8be31a9062d5eff256f1acb51668' #API exemplo, por favor criar uma api key na página https://home.openweathermap.org/api_keys
user_input = input("Digite o nome da cidade: ")

tempoUser = wth.WeatherCity(user_input,api_key)

temperatura = json.loads(tempoUser.get_weather_temperature())
nuvens = json.loads(tempoUser.get_weather_clouds())
vento = json.loads(tempoUser.get_weather_wind())

print('Temperatura:',temperatura['temperature'],'°C')
print('Nuvens:',nuvens['clouds'])
print('Velocidade do vento:',vento['wind'],'m/s')

#Complete Data
print('Data completa em JSON do site OpenWeatherMap:')
print(tempoUser.get_weather_complete_data())
