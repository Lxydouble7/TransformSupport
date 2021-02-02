import pandas as pd
file = pd.read_excel('new TP排期需求&TP字典值.xlsx','TP源文件-宏处理前的排期')

Period = file.iloc[5,1][8:]
Product = file.iloc[4,1][9:]
print(Product)