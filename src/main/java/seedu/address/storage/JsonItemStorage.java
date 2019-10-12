package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ItemStorage;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class that contains all the JSON representation of the string
 */
public class JsonItemStorage implements ItemListStorage{
    public static final String MESSAGE_DUPLICATE_ITEM = "Items list contains duplicate items";

    private Path itemListFilePath;

    public JsonItemStorage (Path path) {
        this.itemListFilePath = path;
    }

    /**
     * Returns the file path of the data file.
     */
    public Path getItemListFilePath() {
        return this.itemListFilePath;
    }


    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param itemStorage cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    public void saveItemStorage(ItemStorage itemStorage) throws IOException{
        saveItemStorage(itemStorage, itemListFilePath);
    }

    public void saveItemStorage(ItemStorage itemStorage, Path filePath) throws IOException{
        requireNonNull(itemStorage);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        FileUtil.writeToFile(filePath, itemStorage.toJson());
    }

    /**
     * Converts this JSON item storage into the model's storage.
     * @return an ItemStorage with all the items
     * @throws IllegalValueException if there were any data constraints violated.
     * @throws IOException if there are any problem with reading from the string.
     */
    public ItemStorage toModelType() throws IllegalValueException, IOException {
        String jsonString = FileUtil.readFromFile(itemListFilePath);
        return ItemStorage.fromJson(jsonString);
    }

}
