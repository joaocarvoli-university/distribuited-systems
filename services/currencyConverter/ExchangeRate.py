import requests


class ExchangeRate:
    def __init__(self, url: str, base):
        """
        :param url: The url of the API about exchange rates
        :param base: The currency base to calculate other rates
        """
        headers = {
            'base': base
        }
        self.response = requests.get(url, headers)
        self.data = self.response.json()

    def get_rates(self) -> dict:
        """
        :return: A dict that contains all currencies available
        """
        if self.data["success"]:
            return self.data["rates"]
        return "You cannot to get the rates"
