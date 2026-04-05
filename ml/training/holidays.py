import holidays

br_holidays = holidays.Brazil()

def add_holiday_feature(df):
    df = df.copy()
    df["is_holiday"] = df["date"].dt.date.apply(
        lambda d: 1 if d in br_holidays else 0
    )
    return df
