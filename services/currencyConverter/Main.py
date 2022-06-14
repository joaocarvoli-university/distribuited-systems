import CurrencyConverter as Cc

converter = Cc.CurrencyConverter()
print(converter.currency_exists('BRL'))
print(converter.currency_exists('USD'))
print(converter.convert('BRL', 'USD', 100))
converter.convert()