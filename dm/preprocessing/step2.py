import pandas as pd

THRESHOLD = 0.001

# 删除变化过小的列
# seller, offerType和v_8对数据变化几乎没有贡献
if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp1.csv")
    var = df.var()
    print(var)

    to_remove = []
    for key, value in var.items():
        if value < THRESHOLD:
            to_remove.append(key)

    print(to_remove)
    df.drop(to_remove, axis=1, inplace=True)
    print(df.head(3))
    df.to_csv("../dataset/temp2.csv", index=False)
