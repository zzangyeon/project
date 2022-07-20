package com.hello.project.domain.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

	@NotBlank
	private String content;
	@NotNull
	private Long articleId;//int는 @NotNull만 됨

}
