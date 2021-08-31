import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# 相关系数测试
# 删掉相关性较低的特征
# 删掉互相相关较强的特征
if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp6.csv")

    df.drop(["reg_d", "create_m", "create_d", "create_y"], axis=1, inplace=True)
    df.drop(["reg_y", "reg_m", "model", "v_13", "v_1", "v_7"], axis=1, inplace=True)
    df.drop(["bodyType", "v_5", "v_9", "v_10", "v_11", "v_12", "v_14"], axis=1, inplace=True)

    col_names = df.columns
    data = []
    for col1 in col_names:
        temp = []
        for col2 in col_names:
            corr = df[col1].corr(df[col2], method="spearman")
            temp.append(corr)
        data.append(temp)

    plt.imshow(data)
    plt.colorbar()
    plt.xticks(np.arange(len(col_names.values)), col_names.values, rotation=90)
    plt.yticks(np.arange(len(col_names.values)), col_names.values)
    plt.savefig("heat.png")

    print(df.head(3))
    df.to_csv("../dataset/final.csv", index=False)
