import json



class Weather:
    def get_weather_clouds(message: json):
        for i in message['arguments']:
            param, value = i.split(':')
            print(param, value)
        


    def get_weather_temperature(message: json):
        print('oi')

    def get_weather_wind(message: json):
        print('oie')
