package com.cms.context.foundation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/29 15:22
 * @Version 1.0
 */
@Getter
@Setter
public class Result<T extends Serializable> implements Serializable {

    private int restCode;
    private String info;
    private T data;
    /**
     * 成功返回 默认返回状态码与信息
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> success(){
        return new Result<>(200,"成功");
    }

    /**
     * 成功返回  默认返回状态码与信息  传入返回数据
     * @param data
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> success(W data){
        return new Result<>(200,data);
    }

    /**
     * 成功返回  默认状态码 传入返回信息与数据
     * @param info
     * @param data
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> success(String info,W data){
        return new Result<>(200,info,data);
    }

    /**
     * 成功返回 传入状态码 成功信息 数据
     * @param code
     * @param info
     * @param data
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> success(int code,String info,W data){
        return new Result<>(code,info,data);
    }

    /**
     * 失败返回 默认状态码与错误信息
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> failed(){
        return new Result<>(500,"错误");
    }

    /**
     * 失败返回 默认状态码 传入返回信息
     * @param info
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> failed(String info){
        return new Result<>(500,info);
    }

    /**
     * 失败返回  传入状态码与返回信息
     * @param code
     * @param info
     * @param <W>
     * @return
     */
    public static <W extends Serializable> Result<W> failed(int code,String info){
        return new Result<>(code,info);
    }


    public Result(int restCode) {
        this.restCode = restCode;
    }

    public Result(int restCode, String info) {
        this.restCode = restCode;
        this.info = info;
    }

    public Result(int restCode, T data) {
        this.restCode = restCode;
        this.data = data;
    }

    public Result(int restCode, String info, T data) {
        this.restCode = restCode;
        this.info = info;
        this.data = data;
    }



}
