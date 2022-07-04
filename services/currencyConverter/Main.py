import CurrencyConverter as Cc

# Example of use
converter = Cc.CurrencyConverter(base='BRL')
print(converter.currency_exists('BRL'))
print(converter.currency_exists('USA'))
print(converter.convert('USD', 'BRL', 100))
print(converter.currencies_available())
