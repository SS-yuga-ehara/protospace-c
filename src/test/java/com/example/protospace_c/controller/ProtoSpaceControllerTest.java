package com.example.protospace_c.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.protospace_c.entity.ProtoTypeEntity;
import com.example.protospace_c.repository.ProtoSpaceRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class ProtoSpaceControllerTest {

  @Mock private ProtoSpaceRepository protoSpaceRepository;

  @InjectMocks private ProtoSpaceController protoSpaceController;

  @Test
  void indexAddsPrototypesToModelAndReturnsIndexView() {
    ProtoTypeEntity prototype = new ProtoTypeEntity();
    prototype.setId(1L);
    prototype.setName("テストプロトタイプ");
    prototype.setImage("https://example.com/image.png");
    prototype.setCatchcopy("テストキャッチコピー");
    prototype.setUserName("山田太郎");
    List<ProtoTypeEntity> prototypes = List.of(prototype);
    when(protoSpaceRepository.findAll()).thenReturn(prototypes);
    Model model = new ExtendedModelMap();

    String viewName = protoSpaceController.index(model);

    assertThat(viewName).isEqualTo("index");
    assertThat(model.getAttribute("prototypes")).isEqualTo(prototypes);
    verify(protoSpaceRepository).findAll();
  }
}
