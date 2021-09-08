import pandas as pd

# 删除交易ID，名字和地区码
# 这些列并没有实际的贡献
if __name__ == '__main__':
    df = pd.read_csv("./dataset/train.csv")
    df.drop(["SaleID", "name", "regionCode"], axis=1, inplace=True)
    print(df.head(3))
    df.to_csv("./dataset/temp1.csv", index=False)
