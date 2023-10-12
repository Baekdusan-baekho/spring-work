package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.FreeDetailResponseDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeModifyResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardService implements IFreeBoardService {
	
	private final IFreeBoardMapper mapper;
	
	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
								.title(dto.getTitle())
								.content(dto.getContent())
								.writer(dto.getWriter())
								.build());
	}

	@Override
	public List<FreeListResponseDTO> getList() {
		List<FreeListResponseDTO> dtoList = new ArrayList<>();
		List<FreeBoard> list = mapper.getList();
		for(FreeBoard board : list) {
			dtoList.add(new FreeListResponseDTO(board));
		}	
		return dtoList;
	}

	@Override
	public FreeDetailResponseDTO getContent(int bno) { // FreeDetailResponseDTO새로 만들어서 사용함
		return new FreeDetailResponseDTO(mapper.getContent(bno)); // freeboardmapper.xml에서 sql 코드가 돌아감
	}

	@Override
	public void update(FreeModifyResponseDTO freeBoard) {
		mapper.update(FreeBoard.builder()
								.title(freeBoard.getTitle())
								.content(freeBoard.getContent())
								.bno(freeBoard.getBno())
								.build());

	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);

	}

}