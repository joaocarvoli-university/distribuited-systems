import os
import ExchangeRate as Er
from dotenv import load_dotenv

load_dotenv()
env = {
    'url_api':os.getenv('URL_API')
}

class CurrencyConverter:
    def __init__(self, base : str):
        self.converter = Er.ExchangeRate(env['url_api'], base)

    def convert(self, currency_from: str, currency_to: str, amount: float) -> float:
        """
        This function convert the currency from some to others
        :param currency_from: A string that represents your actual currency, the currency that you have
        :param currency_to:  A string that represents the currency that you want to convert to
        :param amount: The amount of money from param 'currency_from'
        :return: The value converted
        """
        from_currency = self.converter.get_rates()[currency_from]
        to_currency = self.converter.get_rates()[currency_to]
        conversion = (to_currency * amount)/from_currency
        return conversion

    def currency_exists(self, currency: str) -> bool:
        """
        This function checks if a specific currency exists
        :param currency: The acronyms of currency
        :return:A boolean that represents if this currency exists or not on used api
        """
        if currency in self.converter.get_rates():
            return True
        return False

    def currencies_available(self):
        """
        :return: A list of all currencies available to use
        """
        return list(self.converter.get_rates().keys())
