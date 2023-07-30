package fi.natroutter.hubcore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Color {

    @Setter @Getter
    private int r;

    @Setter @Getter
    private int g;

    @Setter @Getter
    private int b;

}
