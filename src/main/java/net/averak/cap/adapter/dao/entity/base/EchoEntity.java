package net.averak.cap.adapter.dao.entity.base;

import java.time.LocalDateTime;
import javax.annotation.Nonnull;

public class EchoEntity {
    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * echo.id
     *
     * @mbg.generated
     */
    @Nonnull
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * echo.message
     *
     * @mbg.generated
     */
    @Nonnull
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * echo.timestamp
     *
     * @mbg.generated
     */
    @Nonnull
    private LocalDateTime timestamp;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * echo
     *
     * @mbg.generated
     */
    public EchoEntity(String id, String message, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column echo.id
     *
     * @return the value of echo.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column echo.message
     *
     * @return the value of echo.message
     *
     * @mbg.generated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column echo.timestamp
     *
     * @return the value of echo.timestamp
     *
     * @mbg.generated
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
