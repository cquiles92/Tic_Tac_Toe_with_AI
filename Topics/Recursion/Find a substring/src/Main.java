class Util {
    public static int indexOf(String src, String tgt) {
        if (src.contains(tgt)) {
            if (src.startsWith(tgt)) {
                return 0;
            } else if (src.length() > tgt.length()) {
                return 1 + indexOf(src.substring(1), tgt);
            }
        }

        return -1;
    }
}