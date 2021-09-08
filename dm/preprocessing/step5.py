import numpy
import pandas as pd

# 替换异常值
if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp4.csv")
    df.replace(to_replace='-', value=0.5, inplace=True)

    print(df.head(3))
    df.to_csv("../dataset/temp5.csv", index=False)
