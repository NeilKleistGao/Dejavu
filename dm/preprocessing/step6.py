import pandas as pd

# 填充异常值
if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp5.csv")
    print(df["model"].mode())
    print(df["bodyType"].mode())
    print(df["fuelType"].mode())
    print(df["gearbox"].mode())

    df.replace(to_replace="sb", value=0, inplace=True)

    print(df.head(3))
    df.to_csv("../dataset/temp6.csv", index=False)
