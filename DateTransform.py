import calendar
import re
def GetMonth(month):
    return list(calendar.month_abbr).index(month)
print(type(GetMonth('Jan')))