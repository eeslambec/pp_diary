package uz.ppdiary.pp_diary.utils;

import java.io.File;
import java.nio.file.Path;

public interface AppConst {
    Path BASE_PATH = Path.of(System.getProperty("user.home")+ File.separator + "recipe-app"+ File.separator + "attachments" + File.separator);
}
