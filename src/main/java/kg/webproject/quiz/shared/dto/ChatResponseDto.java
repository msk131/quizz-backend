package kg.webproject.quiz.shared.dto;

import kg.webproject.quiz.io.entities.MessageEntity;

import java.util.List;

public class ChatResponseDto {
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private int index;
        private MessageEntity message;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public MessageEntity getMessage() {
            return message;
        }

        public void setMessage(MessageEntity message) {
            this.message = message;
        }
    }
}
