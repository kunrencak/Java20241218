package holiday;

//有給休暇情報を格納したオブジェクト作成

public class PaidHoliday {
    private int grant; // 年間付与日数
    private int duty;  // 取得義務日数
    private int get;   // 取得日数

    public PaidHoliday(int grant, int duty, int get) {
        this.grant = grant;
        this.duty = duty;
        this.get = get;
    }

    public int getGrant() {
        return grant;
    }

    public int getDuty() {
        return duty;
    }

    public int getGet() {
        return get;
    }
}

