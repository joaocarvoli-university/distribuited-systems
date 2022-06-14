import requests


class ExchangeRate:
    def __init__(self, url: str):
        self.response = requests.get(url)
        self.data = self.response.json()

    def get_rates(self):
        if self.data["success"]:
            return self.data["rates"]
        return "You cannot to get the rates"
