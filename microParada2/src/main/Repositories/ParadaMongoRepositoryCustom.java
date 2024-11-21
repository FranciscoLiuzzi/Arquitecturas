package main.Repositories;

import main.Objects.ParadaMongo;

public interface ParadaMongoRepositoryCustom{
    public ParadaMongo findByXAndY(String x, String y);
}