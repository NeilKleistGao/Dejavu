import pandas as pd

# 删除异常值
if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp3.csv")
    df.drop(df[df.power > 600].index, inplace=True)
    print(df.head(3))
    df.to_csv("../dataset/temp4.csv", index=False)
