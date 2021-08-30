import pandas as pd

if __name__ == '__main__':
    df = pd.read_csv("../dataset/temp3.csv")
    df.to_csv("../dataset/temp4.csv", index=False)
