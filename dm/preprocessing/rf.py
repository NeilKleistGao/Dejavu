import pandas as pd
from sklearn.ensemble import RandomForestRegressor
import matplotlib.pyplot as plt
import pickle
import os

# 随机森林
if __name__ == '__main__':
    df = pd.read_csv("../dataset/final.csv")

    y = df["price"].values
    x = df.drop(["price"], axis=1).values

    size = len(df)
    train_size = int(size * 0.9)
    train_x = x[0: train_size, :]
    train_y = y[0: train_size]
    test_x = x[train_size:, :]
    test_y = y[train_size:]

    model = None
    if os.path.exists("../model/rf.pickle"):
        print("load model...")
        with open("../model/rf.pickle", "rb") as fp:
            model = pickle.load(fp)
    else:
        print("train model...")
        model = RandomForestRegressor(n_estimators=96, bootstrap=True, max_features="sqrt")
        model.fit(train_x, train_y)

    res = model.predict(test_x)

    plt.plot(res[0: 100], color='r')
    plt.plot(test_y[0: 100], color='b')
    plt.savefig("rf.png")

    with open("../model/rf.pickle", "wb") as fp:
        pickle.dump(model, fp)

    err = []
    for i in range(len(res)):
        err.append(abs(res[i] - test_y[i]))

    print("MAE: ", sum(err) / len(err))
