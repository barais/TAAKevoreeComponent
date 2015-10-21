package domain;
public class DemoBean implements Comparable{
    public String name;

    public DemoBean(){

    }
    public DemoBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DemoBean demoBean = (DemoBean) o;

        if (!name.equals(demoBean.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public int compareTo(Object o) {
        DemoBean other = (DemoBean)o;
        return name.compareTo(other.name);
    }
}