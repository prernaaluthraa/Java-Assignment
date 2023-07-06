    def __init__(self, ConfigPath):
        self.config = self.load_config(ConfigPath)
        self.SystemException = None

    def load_config(self, ConfigPath):
        df = pd.read_csv(ConfigPath)
        config = {}
        for _, row in df.iterrows():
            config[row['NAME']] = row['VALUE']
        return config
