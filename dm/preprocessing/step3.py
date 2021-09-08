import pandas as pd

# 重新处理时间数据，使得时间可以被量化
if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp2.csv")

    reg = df["regDate"]
    create = df["creatDate"]

    reg_y = reg.map(lambda x: int(str(x)[0: 4]))
    reg_m = reg.map(lambda x: int(str(x)[4: 6]))
    reg_d = reg.map(lambda x: int(str(x)[6: 8]))

    create_y = create.map(lambda x: int(str(x)[0: 4]))
    create_m = create.map(lambda x: int(str(x)[4: 6]))
    create_d = create.map(lambda x: int(str(x)[6: 8]))

    df.insert(0, "create_d", create_d)
    df.insert(0, "create_m", create_m)
    df.insert(0, "create_y", create_y)
    df.insert(0, "reg_d", reg_d)
    df.insert(0, "reg_m", reg_m)
    df.insert(0, "reg_y", reg_y)

    df.drop(["regDate", "creatDate"], axis=1, inplace=True)
    print(df.head(3))
    df.to_csv("../dataset/temp3.csv", index=False)
