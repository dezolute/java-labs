package org.example;

public enum Size {
    XXS,
    XS,
    S,
    M,
    L,
    XL,
    XXL;

    @Override
    public String toString() {
        switch (this) {
            case XXS: return "XXS";
            case XS: return "XS";
            case S: return "S";
            case M: return "M";
            case L: return "L";
            case XL: return "XL";
            case XXL: return "XXL";
            default: return null;
        }
    }
}
