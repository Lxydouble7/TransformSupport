import pandas as pd
import numpy as np
import math
import re
import calendar
file = pd.read_excel('new TP排期需求&TP字典值.xlsx','TP源文件-宏处理前的排期')

Period = file.iloc[5,1][8:]
Product = file.iloc[4,1][9:]

# 第九列为 '总计Total：'即结束
temp = file.iloc[:,[1]]
temp = temp.loc[temp.iloc[:,0]=='Total'].index[0]

# 去掉头尾，剩下表头到最后一行黑行
file = file.iloc[10:temp-1,0:-1]

# 网站名称延用上一个的复制
for i in range(2,len(file)-1):
    if pd.isna(file.iloc[i, 1]) and pd.isna(file.iloc[i + 1, 1]):
        file.iloc[i,1] = file.iloc[i-1,1]


# 先重置行索引
file = file.reset_index(drop=True)

# 获取所有打包价行号package_index
file.iloc[:,1] = file.iloc[:,1].fillna('99999')
package_index = file[(file.iloc[:,1]=='99999')].index.tolist()


# 计算packageid,在网站名称前面插入一列packageid,在packageid前面加入一列package，表示折后打包价
packageidlist = ['','']
package = ['','']

j = 0
packageid = 1
for i in range(2,package_index[-1]):
    if i < package_index[j]:
        packageidlist.append(packageid)
        package.append(file.iloc[package_index[j],11])
    if i == package_index[j]:
        packageid += 1
        j += 1
        packageidlist.append('')
        package.append('')
packageidlist.append('')
package.append('')

file.insert(1,'packageid',packageidlist)
file.insert(1,'package',package)


# 日期补全
pattern = re.compile(r'\w+')
month_list = [18]
i = 0
for t in file.iloc[0]:
    i += 1
    if i > len(file.iloc[0])-1:
        break
    if i > 18 and not pd.isna(file.iloc[0, i]):

        month_list.append(i)

i = 17
j = 0
while i < len(file.iloc[1])-1:
    i += 1
    if j <2 and  i >= month_list[j+1]:
        j += 1
    ans = pattern.findall(str(int(file.iloc[1, i])) + '.' + str(file.iloc[0, month_list[j]]))
    file.iloc[1, i] = ans[2] + '-' + str(list(calendar.month_abbr).index(ans[1])) + '-' + ans[0]

# data = pd.DataFrame(columns=["类型","name","period","MD5","Website","transactionMode","adFormat","device","Market",
#                              "Tracking Imp (Y/N)\n可否曝光监测","Tracking Click (Y/N)\n可否点击监测","总价\nTotalNetCost",
#                              "折后打包价\nPackage:","packageid","总广告曝光(000')\nTotalImp(000')","预估总广告点击数字\nEstedTotalClicks",
#                              "日期","打点信息","adFormat&device"])
#
# data=data.append(["TP",],ignore_index=True)


#print(data)
#data.to_csv('test.csv',encoding="gb18030",index=False)
#file.to_csv('out.csv',encoding="gb18030", header=None)
