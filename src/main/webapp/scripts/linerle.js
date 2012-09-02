function linerle_input_value(v) {
    return typeof v == 'object' ? JSON.stringify(v) : v;
}