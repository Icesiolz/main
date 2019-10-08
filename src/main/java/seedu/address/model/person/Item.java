package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import static java.util.Objects.requireNonNull;
import java.util.*;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Item in Elisa.
 * Guarantees: ItemDescription is present and not null. 
 * At least one of the following three fields (Task, Event, Reminder) is present and not null.
 */
public class Item {

    // Identity fields
    private final Task task;
    private final Event event;
    private final Reminder reminder;
    
    // Data fields
    private final ItemDescription itemDescription;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    private Item(Task task, Event event, Reminder reminder,
                 ItemDescription itemDescription, Set<Tag> tags) {
        requireAllNonNull(itemDescription, tags);
        this.task = task;
        this.event = event;
        this.reminder = reminder;
        
        this.itemDescription = itemDescription;
        this.tags.addAll(tags);
    }

    public boolean hasTask() { return task != null; }
    
    public boolean hasEvent() { return event != null; }
    
    public boolean hasReminder() { return reminder != null; }
    
    public Optional<Task> getTask() {
        if(this.task == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.task);
        }
    }
    
    public Optional<Event> getEvent() {
        if(this.event == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.event);
        }
    }
    
    public Optional<Reminder> getReminder() {
        if(this.reminder == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.reminder);
        }
    }
    
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    /**
     * Change ItemDescription
     */
    public boolean setItemDescription(ItemDescription) {
        ItemDescription
    }

    /**
     * Change Task referenced
     */
    public boolean setTask(Task newTask) {
        ItemDescription
    }

    /**
     * Change Event referenced
     */
    public boolean setEvent(Event newEvent) {
        ItemDescription
    }

    /**
     * Change Reminder referenced
     */
    public boolean setReminder(Reminder newReminder) {
        ItemDescription
    }
    
    /**
     * Returns true if both items have the same task referenced.
     * This defines a weaker notion of equality between two items.
     */
    public boolean hasSameTask(Item otherItem) {
        return void;        
    }

    /**
     * Returns true if both items have the same Event referenced.
     * This defines a weaker notion of equality between two items.
     */
    public boolean hasSameEvent(Item otherItem) {

    }

    /**
     * Returns true if both items have the same Reminder referenced.
     * This defines a weaker notion of equality between two items.
     */
    public boolean hasSameReminder(Item otherItem) {

    }
    
    /**
     * Returns true if both items have the same identity and data fields.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getTask().equals(getTask())
                && otherItem.getReminder().equals(getReminder())
                && otherItem.getEvent().equals(getEvent())
                && otherItem.getItemDescription().equals(getItemDescription())
                && otherItem.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(task, event, reminder, itemDescription, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    public static class ItemBuilder {
    
        // Identity fields
        private Task task;
        private Event event;
        private Reminder reminder;
    
        // Data fields
        private ItemDescription itemDescription;
        private Set<Tag> tags = new HashSet<>();
        
        public ItemBuilder() {}
        
        public ItemBuilder setTask(Task task) {
            requireNonNull(task);
            this.task = task;
            return this;
        }
        
        public ItemBuilder setEvent(Event event) {
            requireNonNull(event);
            this.event = event;
            return this;
        }
        
        public ItemBuilder setReminder(Reminder reminder) {
            requireNonNull(reminder);
            this.reminder = reminder;
            return this;
        }
        
        public ItemBuilder setDescriptor(ItemDescription descriptor) {
            requireNonNull(descriptor);
            this.itemDescription = descriptor;
            return this;
        }
        
        public ItemBuilder setTags(Set<Tag> tags) {
            requireNonNull(tags);
            this.tags = tags;
            return this;
        }
        
        public Item build() throws IllegalArgumentException{
            Item newItem = new Item(this.task, this.event, this.reminder, this.itemDescription, this.tags);
            
            //Validation of parameters of object after object has been created.
            //Validate after object has been created as per StackOverflow link
            //https://stackoverflow.com/questions/38173274/builder-pattern-validation-effective-java
            //However this seems to be contrary to the answer in the following link
            //https://stackoverflow.com/questions/12930852/clearing-doubts-about-the-builder-pattern
            //However it seems safer to follow the first as the object fields could be mutated after it has been copied 
            // from the builder to the object.
            if (newItem.getItemDescription() == null) {
                throw new IllegalArgumentException("Description must be provided!");
            }
            if (newItem.getTask() == null && newItem.getEvent() == null && newItem.getReminder() == null) {
                throw new IllegalArgumentException("Task, Event & Reminder cannot all be empty!");
            }
            
            //Resetting all constructing parameters back to null, so a new object doesn't use the parameters of the 
            // previous object.
            task = null;
            event = null;
            reminder = null;
            itemDescription = null;
            tags = null;
            
            return newItem;
        }
        
    }
}