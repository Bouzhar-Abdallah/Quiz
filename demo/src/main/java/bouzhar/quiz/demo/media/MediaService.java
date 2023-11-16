package bouzhar.quiz.demo.media;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MediaService(MediaRepository mediaRepository, ModelMapper modelMapper) {
        this.mediaRepository = mediaRepository;
        this.modelMapper = modelMapper;
    }


    public Media addMedia(Media media){
        //mediaRepository.save(modelMapper.map(mediaDto,Media.class));
        return mediaRepository.save(media);
    }
    public List<Media> addMedias(List<Media> mediaList){
        return mediaRepository.saveAll(mediaList);
    }
}
