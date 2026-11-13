package com.bej.kanbanService.service;


import com.bej.kanbanService.domain.*;
import com.bej.kanbanService.exception.*;
import com.bej.kanbanService.repository.UserRepository;
import com.bej.kanbanService.utils.KANBANUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KanbanBoardServiceImpl implements IKanbanBoardService{

    @Autowired

    private UserRepository userRepository;

    @Autowired
    public KanbanBoardServiceImpl(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveBoard(KanbanBoard kanbanBoard , String userId) throws UserNotFoundException, LimitReachedException, BoardAlreadyExistException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        if (user.getKanbanBoardList() == null) {
            user.setKanbanBoardList(Arrays.asList(kanbanBoard));
        } else {
            List<KanbanBoard> kanbanBoards = user.getKanbanBoardList();
            if (kanbanBoards.size()>=3){
                throw new LimitReachedException();
            }

            // Check if the task board exists in the taskList
            boolean boardAlreadyExists = kanbanBoards.stream()
                    .anyMatch(existingTask -> existingTask.getKanbanBoardId().equals(kanbanBoard.getKanbanBoardId()));

            if (boardAlreadyExists) {
                throw new BoardAlreadyExistException();
            }
            kanbanBoards.add(kanbanBoard);
            user.setKanbanBoardList(kanbanBoards);
        }
        return userRepository.save(user);
    }


    @Override
    public User deleteBoard(String userId, String boardId) throws UserNotFoundException, NoItemsInListException, BoardNotFoundException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        List<KanbanBoard> kanbanBoardList = user.getKanbanBoardList();

        KanbanBoard foundBoard = KANBANUtils.getKanbanBoard(user,  boardId);

        if (foundBoard != null) {
            kanbanBoardList.remove(foundBoard);
            user.setKanbanBoardList(kanbanBoardList);
        }

        return userRepository.save(user);
    }

    @Override
    public List<KanbanBoard> getAllBoards(String userId) throws UserNotFoundException, NoItemsInListException {

        User user = KANBANUtils.getUserById(userRepository, userId);

        List<KanbanBoard> kanbanBoardList = user.getKanbanBoardList();

        if (kanbanBoardList.isEmpty()) {
            throw new NoItemsInListException();
        }

        return kanbanBoardList;
    }


}
